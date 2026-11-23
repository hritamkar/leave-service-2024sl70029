
pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/hritamkar/leave-service-2024sl70029.git'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean verify'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Code Inspection') {
            steps {
                withCredentials([
                    string(credentialsId: 'sonarqube-token',
                           variable: 'SONAR_TOKEN')
                ]) {
                    sh '''
                        mvn org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar \
                          -Dsonar.host.url=http://localhost:9000 \
                          -Dsonar.projectKey=leave-service \
                          -Dsonar.login=$SONAR_TOKEN
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t leave-service:1.0 .'
            }
        }

        stage('Deploy to Staging') {
            steps {
                sh '''
                    docker rm -f leave-service-staging || true

                    docker run -d \
                      --name leave-service-staging \
                      -p 8085:8080 \
                      -e APP_ENV=staging \
                      leave-service:1.0

                    sleep 5

                    curl -f http://localhost:8085
                '''
            }
        }
    }
}
