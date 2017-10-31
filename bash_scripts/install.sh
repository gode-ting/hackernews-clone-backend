#!/bin/bash
set -x # Show the output of the following commands (useful for debugging)

# Import the SSH deployment key
openssl aes-256-cbc -K $encrypted_c064a258234d_key -iv $encrypted_c064a258234d_iv - in travis_rsa.enc -out travis_rsa -d
rm travis_rsa .enc # Don't need it anymore
chmod 600 travis_rsa 
mv travis_rsa  ~/.ssh/id_rsa