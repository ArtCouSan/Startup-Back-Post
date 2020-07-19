pipeline {
    agent any
    tools {
        Maven '3.6.3'
    }
    environment { 
        CI = 'true'
    }
    stages {
        stage('Build') {
            steps {
                sh 'cd posts/'
                sh 'mvn -version'
                sh 'mvn clean install' 
            }
            post {
                always {
                    cleanWs()
                }
            }
        }
    }
}