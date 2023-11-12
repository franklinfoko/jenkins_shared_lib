def call(String ecsService, String taskDefinition, String revision, String ecsCluster, String count) {

    withCredentials([[
                    $class: 'AmazonWebServicesCredentialsBinding',
                    credentialsId: 'aws-credentials',
                    accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                    secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
                ]]) {
                    sh 'aws ecs update-service --force-new-deployment --service ${ecsService} --task-definition ${taskDefinition}:${revision} --cluster ${ecsCluster}  --desired-count ${count}'
                }
}