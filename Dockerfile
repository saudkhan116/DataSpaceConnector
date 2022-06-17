# build stage
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
RUN apk add curl
COPY . .
RUN npm run build

# production stage
FROM nginx:stable-alpine as production-stage

# create data directory where the battery passport is kept
RUN mkdir data

COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 8080

CMD ["nginx", "-g", "daemon off;"]