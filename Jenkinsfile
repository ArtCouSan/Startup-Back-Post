pipeline {
    agent any
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
        stage('Test') {
            steps {
                sh 'cd posts/'
                sh 'mvn -Dtest=br.com.posts.** test' 
            }
        }
    }
}