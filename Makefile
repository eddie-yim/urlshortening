.PHONY: test dev build down
test:
	./gradlew clean test --info
build:
	./gradlew clean bootJar --info
dev:
	$(MAKE) build
	docker-compose -f docker-compose.dev.yml up --build -d
down:
	docker-compose -f docker-compose.dev.yml down


