pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''#!/bin/bash
TIMESTAMP=$(date --utc +%FT%TZ)
WEBHOOK_URL=\'https://discordapp.com/api/webhooks/538654328718884877/GkJ_dxsIN6NaK2oKumjiH6ihW-VGPU1hzsssnYzgEtQs2tGcmv0qYeeg656tJSz0eDLd\'
WEBHOOK_DATA=\'{
  "embeds": [
    {
      "title": "Jenkins Build: HypeBeastMc/Hypercore",
      "description": "Starting build of project HypebeastMC/Hypercore\\nusing `mvn clean package verify`",
      "color": 512550,
      "timestamp": "\'"$TIMESTAMP"\'",
      "footer": {
        "icon_url": "",
        "text": "Blue Ocean for Jenkins"
      }
    }
  ]
}\'
(curl --fail --progress-bar -A "Jenkins-Webhook" -H Content-Type:application/json -H X-Author:JoeZwet#6252 -d "$WEBHOOK_DATA" "$WEBHOOK_URL" \\
  && echo -e "\\\\n[Webhook]: Successfully sent the webhook.") || echo -e "\\\\n[Webhook]: Unable to send webhook."'''
        git 'https://github.com/HypeBeastMC/Hypercore.git'
        build 'Hypercore'
      }
    }
  }
}