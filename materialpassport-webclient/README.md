# materialpass-webclient

This battery pass consumer application is used with the Asset Administration Shell (AAS).


### Cloning repository

```bash
git clone https://github.com/saudkhan116/DataSpaceConnector.git
```
### Launch docker containers for the Asset Administration Shell (AAS)
```bash
cd materialpassport-webclient/aas/getting-started-guide/
docker compose up -d
```

### Init Data Provider
```bash
cd ..

# Executing this script will create a sample battery passport data, create EDC asset, policies, contract definitions and register it as a digital twin inside registry.
./init-provider.sh
```

## Project setup
```
cd ..
npm install --legacy-peer-deps
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
