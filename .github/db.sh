#!/usr/bin/env bash

mysql -uroot -p $(mariaRootPassword) < data/1.users.SQL
mysql -uroot -p $(mariaRootPassword) < data/2.RECORD.SQL
mysql -uroot -p $(mariaRootPassword) < data/3.CHANGES.SQL