const fs = require('fs');
const semver = require('semver-extra');
const Promise = require('promise');

/**
 * Returns a promise, which returns the latest version of
 * the './versions'-folder
 */
function getLatestVersion() {
    return new Promise((resolve, reject) => {
        fs.readdir('./versions/', (err, files) => {
            if (err) {
                reject(err);
            }
            let versions = files
                .filter(file => file !== 'latest-version.txt' && file !== 'latest');
            resolve(semver.max(versions));
        });
    });
}

/**
 * Returns a promise with the content of 
 * the './versions/latest-version.txt'-file
 */
function getLatestVersionTxtContent() {
    return new Promise((resolve, reject) => {
        fs.readFile('./versions/latest-version.txt', 'utf8', (err, content) => {
            if (err) {
                reject(err);
            }
            // Remove linebreaks etc.
            resolve(content.replace(/(\r\n|\n|\r)/gm, ''));
        });
    });
}

module.exports = { getLatestVersionTxtContent, getLatestVersion };