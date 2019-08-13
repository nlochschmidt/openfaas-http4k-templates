#!/bin/bash

set -e
set -o errexit
set -o pipefail
set -o nounset

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

LOCAL_VERSION=`cat function/versions.gradle | grep "ext.http4k_version" | sed -E "s/.*\'(.*)\'/\1/g"`

BINTRAY_VERSION=`curl -s https://bintray.com/api/v1/packages/http4k/maven/http4k-core/versions/_latest | jq -r .name`

if [[ "$LOCAL_VERSION" == "$BINTRAY_VERSION" ]]; then
    echo "Version has not changed"
    exit 0
fi

echo "Upgrading http4k from $LOCAL_VERSION to $BINTRAY_VERSION"

sed -i '' "s/.*ext.http4k_version = '.*'/    ext.http4k_version = '$BINTRAY_VERSION'/g" function/versions.gradle

git add function/versions.gradle
