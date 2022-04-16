FROM gradle:7.4.2-jdk11 as build

RUN mkdir -p /gradlebuild
WORKDIR /gradlebuild

COPY . .

RUN gradle installDist --no-daemon

FROM woov/jre11-grpc as runtime

WORKDIR /home
COPY --from=build /gradlebuild/app/build/install .

EXPOSE 8080
ENTRYPOINT "/home/Coda45PromAdapter/bin/Coda45PromAdapter"
