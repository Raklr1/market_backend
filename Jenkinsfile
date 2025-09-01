pipeline {
    agent any
    tools{
        jdk 'JDK-21'
        maven 'Maven'
        nodejs 'nodejs'
    }

    stages {
        stage('后端构建') {
            steps {
                
                    //bat 'mvn install'
                    bat 'docker rmi market-back:v1'
                    bat 'docker build -t market-back:v1 .'
                    bat 'minikube image load market-back:v1'
                
            }
        }
        
    }
    post {
        always {
            echo "后端构建完成"
        }
    }
}

