# Installing PostgreSQL 18 on Amazon Linux 2023

These steps install PostgreSQL 18 (the official PostgreSQL Global Development Group build) on an Amazon Linux 2023 EC2 instance. Amazon Linux 2023 does not ship PostgreSQL 18 in its own repositories, so this pulls it from the official PostgreSQL YUM repository instead.

## Prerequisites

- An Amazon Linux 2023 EC2 instance
- Logged in as `ec2-user` (or another user with sudo access)
- Internet access from the instance (default for most VPC setups)

## Step 1: Add the PostgreSQL repository

```bash
sudo bash -c 'printf "[pgdg18]\nname=PostgreSQL 18 for RHEL 9 - x86_64\nbaseurl=https://download.postgresql.org/pub/repos/yum/18/redhat/rhel-9-x86_64\nenabled=1\ngpgcheck=0\n" > /etc/yum.repos.d/pgdg18.repo'
```

This writes a repository definition file pointing at the RHEL 9 build of PostgreSQL 18, which is compatible with Amazon Linux 2023.

Note: the official PostgreSQL installer package (`pgdg-redhat-repo-latest.noarch.rpm`) will not install on Amazon Linux 2023, because it checks for a file called `/etc/redhat-release` that only exists on genuine RHEL systems. Writing the repository file directly, as above, avoids that check entirely.

## Step 2: Install PostgreSQL 18

```bash
sudo dnf install -y postgresql18-server
```

This installs both the server and the client tools (`psql` and friends come along automatically as a dependency).

## Step 3: Initialize the database

```bash
sudo /usr/pgsql-18/bin/postgresql-18-setup initdb
```

This sets up the initial data directory. It only needs to be run once.

## Step 4: Start PostgreSQL and enable it on boot

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

**"Problem: conflicting requests... nothing provides /etc/redhat-release"**
This means Step 1 was done using the old RPM-based method instead of the `printf` command above. Use the `printf` command in Step 1 instead; it does not have this problem.

**"Repository not found" or similar error on Step 2**
Double-check the repository file was created correctly:

```bash
cat /etc/yum.repos.d/pgdg18.repo
```

It should show five lines starting with `[pgdg18]`. If the file is missing or empty, re-run Step 1.

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
