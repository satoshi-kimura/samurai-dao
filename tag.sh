#!/bin/bash

set -eu
: $1

git tag $1
git push origin $1
