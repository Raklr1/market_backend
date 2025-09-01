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
                    withKubeConfig([credentialsId: 'k8s1']) {
                    
                    //bat 'mvn install'
                    //bat 'docker rmi market-back:v1'
                    //bat 'docker build -t market-back:v1 .'
                    //bat 'minikube start'
                    //bat 'docker image ls'
                    //bat 'minikube image load market-back:v1'
                    //bat 'minikube image ls'
                    //bat 'kubectl get pods'
                    //bat 'minikube image load market-back:v1'
                    bat 'minikube version'
                    bat 'minikube update-context'
                    bat 'echo %KUBECONFIG%'
                    //bat 'minikube start'
                    bat 'minikube status'
                    bat 'minikube image ls'
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

