pipeline {
    agent any

    stages {
        stage('后端构建') {
            steps {
                
                    bat 'mvn install'
                    bat 'docker build -t market-back:v1 .'
                
            }
        }
        

    post {
        always {
            echo "后端构建完成"
        }
    }
}
