# 단축 URL 생성기 예제
Alphanumeric 62진수 기반의 단축 URL 생성기 예제

# 애플리케이션 실행
## Prerequisite
### Docker 및 Docker Compose 설치
#### Ubuntu
```shell
# Update package manager & install packages
sudo apt-get update
sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg \
    lsb-release

# Add GPG key
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

# Install docker engine
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io

# Install docker compose
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

# Grant permission
sudo chmod +x /usr/local/bin/docker-compose

# Symlink
sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose

# Check installed
docker-compose --version
```
#### Mac
```shell
# Homebrew package manager
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
brew update
brew install --cask docker
```
## Application 실행
```shell
make dev
```
production 배포 시 스프링부트의 application.prod.yml및 docker-compose 파일의 환경변수 수정 등 필요.
# 테스트 실행
Java 11+ 설치 필요
```aidl
make test
```

