def call(String imageRepoName, String imageTag, String hubUser, String awsDefaultregion, String awsAccountId) {
    // push on docker hub
    withCredentials([usernamePassword(
        credentialsId: 'dockerhub',
        passwordVariable: 'DOCKERHUB_PASSWORD',
        usernameVariable: 'DOCKERHUB_USER'
    )]) {
        sh """
         docker login -u '$DOCKERHUB_USER' -p '$DOCKERHUB_PASSWORD'
         docker push ${hubUser}/${imageRepoName}:${ImageTag}
        """ 
    }

    // push on ECR
    withCredentials([[
                    $class: 'AmazonWebServicesCredentialsBinding',
                    credentialsId: 'aws-credentials',
                    accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                    secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
                ]]) {
                    sh """
                     aws ecr get-login-password --region ${awsDefaultregion} | docker login --username AWS --password-stdin ${awsAccountId}.dkr.ecr.${awsDefaultregion}.amazonaws.com
                     docker push ${awsAccountId}.dkr.ecr.${awsDefaultregion}.amazonaws.com/${imageRepoName}:${imageTag}
                    """
                }
}