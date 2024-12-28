# Create mvnw script
echo "mvn \$@" > mvnw
chmod +x mvnw

# Create mvnw.cmd script for Windows
echo "mvn %*" > mvnw.cmd
chmod +x mvnw.cmd

