pipeline {
    agent any
    tools{
        jdk 'JDK-21'
        maven 'Maven'
        nodejs 'nodejs'
        docker 'docker'
    }

    stages {
        stage('后端构建') {
            steps {
                    withKubeConfig([credentialsId: 'k8s1']) {
                    bat 'minikube version'
                    bat 'minikube status'
                    //bat 'mvn install'
                    //bat 'docker rmi market-back:v1'
                    //bat 'docker build -t market-back:v1 .'
                    //bat 'minikube start'
                    bat 'docker image ls'
                    //bat 'minikube image load market-back:v1'
                    //bat 'minikube image ls'
                    bat 'kubectl get pods'
                    //bat 'minikube image load market-back:v1'
                        
                    }
                
            }
        }
        
    }
    post {
        always {
            echo "后端构建完成"
        }
    }
}

