const fs = require('fs');
const Promise = require('promise');
const moment = require('moment');

const TIMESTAMP_FORMAT = 'YYYYMMDDhhmmss';
const SEMVER_REGEX = /^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(-(0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*)(\.(0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*))*)?(\+[0-9a-zA-Z-]+(\.[0-9a-zA-Z-]+)*)?$/gm;

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
                .filter(file => file !== 'latest-version.txt' && file !== 'latest')
                // Ignore Semver Versions
                .filter(file => !new RegExp(SEMVER_REGEX).test(file))
                .map(timestamp => moment(timestamp, TIMESTAMP_FORMAT))
                // Sort the by date
                .sort((date1, date2) => moment.utc(date1.timeStamp).diff(moment.utc(date2.timeStamp)));
            resolve(versions[versions.length - 1].format(TIMESTAMP_FORMAT));
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
