var exec = require('cordova/exec')

exports.discover = function (successCallback, errorCallback) {
  exec(successCallback, errorCallback, 'Itos', 'discover')
}

exports.print = function (
  textToPrint, fontSize, align, bold, successCallback, errorCallback) {
  exec(successCallback, errorCallback, 'Itos', 'print',
    [textToPrint, fontSize, align, bold],
  )
}
