# Installing PostgreSQL 18 on Amazon Linux 2023

These steps install PostgreSQL 18 (the official PostgreSQL Global Development Group build) on an Amazon Linux 2023 EC2 instance. Amazon Linux 2023 does not ship PostgreSQL 18 in its own repositories, so this pulls it from the official PostgreSQL YUM repository instead.

## Prerequisites

- An Amazon Linux 2023 EC2 instance
- Logged in as `ec2-user` (or another user with sudo access)
- Internet access from the instance (default for most VPC setups)

## Step 1: Add the PostgreSQL YUM repository

```bash
sudo dnf install -y https://download.postgresql.org/pub/repos/yum/reporpms/EL-9-x86_64/pgdg-redhat-repo-latest.noarch.rpm
```

This installs a repository definition pointing at the RHEL 9 build of PostgreSQL, which is compatible with Amazon Linux 2023.

## Step 2: Fix the repository path

```bash
sudo sed -i 's/$releasever/9/g' /etc/yum.repos.d/pgdg-redhat-all.repo
```

Amazon Linux 2023 does not set `$releasever` to `9` the way RHEL 9 does, so the repository file has to be told explicitly which release to use. Without this step, the next command will fail with a "repository not found" style error.

## Step 3: Install PostgreSQL 18

```bash
sudo dnf install -y postgresql18-server
```

This installs both the server and the client tools (`psql` and friends come along automatically as a dependency).

## Step 4: Initialize the database

```bash
sudo /usr/pgsql-18/bin/postgresql-18-setup initdb
```

This sets up the initial data directory. It only needs to be run once.

## Step 5: Start PostgreSQL and enable it on boot

```bash
sudo systemctl enable --now postgresql-18
```

## Verifying the installation

Check that the service is running:

```bash
sudo systemctl status postgresql-18
```

You should see `active (running)`.

Check the version directly:

```bash
sudo -u postgres /usr/pgsql-18/bin/psql -c "select version();"
```

## Optional: add PostgreSQL to your PATH

PostgreSQL 18 installs into `/usr/pgsql-18/bin/`, which is not on the default `PATH`. To be able to just type `psql` instead of the full path, run:

```bash
echo 'export PATH=$PATH:/usr/pgsql-18/bin' >> ~/.bash_profile
source ~/.bash_profile
```

## Troubleshooting

**"Repository not found" or similar error on Step 3**
Re-check Step 2 ran without errors. This is almost always caused by the `$releasever` substitution not having happened.

**Checking if PostgreSQL is already installed before starting**

```bash
rpm -qa | grep -i postgres
dnf list installed | grep -i postgres
```

If either of these returns results, PostgreSQL is already on the system. Do not repeat the steps above; instead check the installed version with:

```bash
sudo -u postgres psql -c "select version();"
```

(adjust the path to `psql` if it isn't on your `PATH`)
