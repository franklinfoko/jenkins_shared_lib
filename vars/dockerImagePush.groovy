def call(String imageRepoName, String imageTag, String hubUser) {
    withCredentials([usernamePassword(
        credentialsId: 'dockerhub',
        passwordVariable: 'DOCKERHUB_PASSWORD',
        usernameVariable: 'DOCKERHUB_USER'
    )]) {
        sh "docker login -u '$DOCKERHUB_USER' -p '$DOCKERHUB_PASSWORD'"
    }
    sh "docker push ${ubUser}/${imageRepoName}:${ImageTag}"
}