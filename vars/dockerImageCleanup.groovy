def call(String imageRepoName, String awsAccountId, String imageTag, String awsDefaultregion, String hubUser) {
    
    sh """
     docker rmi ${imageRepoName}:${imageTag} 
     docker rmi ${hubUser}/${imageRepoName}:${ImageTag}
     docker rmi ${awsAccountId}.dkr.ecr.${awsDefaultregion}.amazonaws.com/${imageRepoName}:${imageTag}
    """
}