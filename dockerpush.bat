cd C:\Users\vitga\IdeaProjects\hudeem
call gradlew build
call docker build -t vitgavrilchenko/hudeem-image .
call docker push vitgavrilchenko/hudeem-image