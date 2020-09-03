const request = require('request');

const FETCH_URL = 'https://raw.githubusercontent.com/pokemongo-dev-contrib/pokemongo-game-master/master/versions';
const allowedFormats = ['protobuf', 'json'];

/**
 * Returns the content of the request version
 * @param {String} version The version you want to request. Can be a specific version (e.g. '0.83.3') or latest version (e.g. 'latest')
 * @param {'json'|'protobuf'} format The format of the version. Can be either 'json' or 'protobuf'.
 * @returns {Object|String} Returns a object, if format 'json' is given, or a string
 * 
 * @example
 * const gameMaster = require('pokemongo-game-master');
 * 
 * gameMaster.getVersion('latest', 'json').then(console.log); // Returns as object: { itemTemplates: [ ... ], timestampMs: '1512514949791' }
 * gameMaster.getVersion('latest', 'protobuf').then(console.log); // Returns as string
 */
const getVersion = (version, format) => {
    format = (format || 'json').toLowerCase();
    return new Promise((resolve, reject) => {
        // Validate Format
        if (!allowedFormats.includes(format)) {
            reject(`Only the format .${allowedFormats.join(' .')} are allowed`);
        }
        // Get Date
        request.get(`${FETCH_URL}/${version}/V2_GAME_MASTER.${format}`, (error, response, body) => {
            if (response.body === '404: Not Found\n') return reject(new Error('Given version could not be found.'));
            if (error) return reject(error);

            // Parse output, if JSON is selected
            if (format === 'json') {
                try {
                    resolve(JSON.parse(body));
                } catch (exception) {
                    reject(exception);
                }
                // Return as string
            } else {
                resolve(body);
            }
        });
    });
};

/**
 * Returns the number of the latest version
 * @returns {String} Returns the latest version number
 * 
 * @example
 * const gameMaster = require('pokemongo-game-master');
 * 
 * const version = await gameMaster.getLatestVersionName);
 * console.log(version) // E.g. 0.89.0
 */
const getLatestVersionName = () => {
    return new Promise((resolve, reject) => {
        const URL = `${FETCH_URL}/latest-version.txt`;
        request.get(URL, (error, response, body) => {
            if (response.body === '404: Not Found\n') return reject(new Error(`${URL} was not found. Seems like an internal error`));
            if (error) return reject(error);
            resolve(body);
        });
    });
};

module.exports = { getVersion, getLatestVersionName };
