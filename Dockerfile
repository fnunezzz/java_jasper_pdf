
FROM openjdk:17 as build-stage
ARG APP_VER=1.0.0
ENV TZ=America/Sao_Paulo

WORKDIR /usr/local/build/
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

RUN chmod +x *
RUN ./mvnw install -DskipTests

FROM amazoncorretto:17 as deploy-stage

ENV TZ=America/Sao_Paulo \
JASPER_PATH=/usr/local/app/assets

# Microsoft core fonts e.g. ARIAL
RUN amazon-linux-extras install epel -y && \
    yum install -y wget && \
    yum install -y tar && \
    yum install -y curl cabextract xorg-x11-font-utils fontconfig && \
    rpm -i https://downloads.sourceforge.net/project/mscorefonts2/rpms/msttcore-fonts-installer-2.6-1.noarch.rpm && \
    unlink /etc/localtime \
    && ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime \
    && echo $TZ > /etc/timezone

WORKDIR /usr/local/app/

COPY --from=build-stage /usr/local/build/target/*.war ./app.war

COPY assets/ ./assets
COPY startup.sh .

RUN chmod +x startup.sh
