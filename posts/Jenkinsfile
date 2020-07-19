pipeline {
    agent any
	tools {
		maven '3.6.3'
        dockerTool 'docker'
	}
    environment { 
        CI = 'true'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -version'
                sh 'cd posts/; mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'cd posts/; mvn -Dtest=br.com.posts.** test' 
            }
        }
        stage('Delivery') {
            steps {
                sh 'docker-compose up -d' 
            }
        }
    }
}