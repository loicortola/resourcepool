function MainCtrl($scope, i18nService) {

  if(i18nService.isLocaleEmpty())
    i18nService.setLocales({
      'default': '../../i18n/resources-locale_en.json'
    },true);

}

app.controller('MainCtrl', ['$scope', 'i18nService', MainCtrl]);