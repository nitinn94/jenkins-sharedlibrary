// vars/deployWithAnsible.groovy

def call(Map params) {
    def dockerImage = params.dockerImage
    def dockerTag = params.dockerTag
    def kubeconfig = params.kubeconfig
    def awsAccessKeyId = params.awsAccessKeyId
    def awsSecretAccessKey = params.awsSecretAccessKey
    def ansiblePlaybookPath = params.ansiblePlaybookPath ?: "ansible/deploy_k8s.yaml"  // Default path

    // Run the ansible-playbook command
    sh """
    ansible-playbook $ansiblePlaybookPath \
        -e docker_image=$dockerImage:$dockerTag \
        -e kubeconfig=$kubeconfig \
        --extra-vars "aws_access_key_id=$awsAccessKeyId aws_secret_access_key=$awsSecretAccessKey"
    """
}
