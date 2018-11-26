const settings = {
    backendUrl: (url) => url == '/games' ? '/games/index.json' : `${url}.json`
};