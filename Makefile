.PHONY: test dev down
test:
	./gradlew clean test --info
dev:
	docker-compose -f docker-compose.dev.yml up --build -d
down:
	docker-compose -f docker-compose.dev.yml down


