const chai = require('chai');

const expect = chai.expect;

const versionsFolder = require('./modules/versions-folder');

describe('\'./versions\'-Folder', () => {
    it('./versions/latest-version.txt should have the latest version', done => {
        versionsFolder
            .getLatestVersion()
            .then((latestVersion) => {
                versionsFolder
                    .getLatestVersionTxtContent(latestVersion)
                    .then((latestVersionTxtContent) => {
                        expect(latestVersionTxtContent).to.equal(latestVersion);
                        done()
                    })
                    .catch(err => done(new Error(err)));
            });
    });
});
