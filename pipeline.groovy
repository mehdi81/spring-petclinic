pipeline {
    agent any

    triggers {
        pollSCM ('* * * * *')
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/mehdi81/spring-petclinic.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean compile'
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}