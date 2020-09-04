#!/bin/bash
echo "Resetting Employee database"
mysql -h services -utodo -ptodo < mydb.sql
echo "Success!"
