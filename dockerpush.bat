cd E:\Projects\VitRepo\hudeem
call gradlew build
call docker build -t adrianvved/hudeem-backend .
call docker push adrianvved/hudeem-backend