var exec = require('cordova/exec');

exports.discover = function (successCallback, errorCallback) {
  exec(successCallback, errorCallback, 'Itos', 'discover');
};

exports.print = function (macAddress, textToPrint, successCallback, errorCallback) {
  exec(successCallback, errorCallback, 'Itos', 'print', [macAddress, textToPrint]);
};
