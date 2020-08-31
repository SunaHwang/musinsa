#!/bin/sh

echo "===install util==="
apt-get install openjdk-13-jre

apt-get install openjdk-13-jdk

sudo apt-get install git

sudo apt-get install maven

java -jar lombok.jar


echo "===clone project==="
git clone https://github.com/SunaHwang/musinsa.git\


echo "===create schema==="
CREATE SCHEMA `test`;


echo "===build==="
cd musinsa

mvn package


echo "===execute project==="
mv target

java -jar musinsa-0.0.1-SNAPSHOT.jar
