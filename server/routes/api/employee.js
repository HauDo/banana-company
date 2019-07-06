var router = require('express').Router(),
    moment = require('moment')
	;

var TIME_FORMAT = 'HH:mm:ss';

router.post('/checkin', function(req, res, next) {
  	if (!req.body.empid || !req.body.account || !req.body.localtime) {
    	return res.status(422).json({
    		errors: {
    			empid: "can't be blank",
    			account: "can't be blank",
    			localtime: "can't be blank"
    		}
    	});
  	}

  	return res.status(200).json({
  		message: "You checked in at " + moment(new Date()).format(TIME_FORMAT) + ". Enjoy your day."
  	});
});

router.post('/checkout', function(req, res, next) {
  	if (!req.body.empid || !req.body.account || !req.body.localtime) {
    	return res.status(422).json({
    		errors: {
    			empid: "can't be blank",
    			account: "can't be blank",
    			localtime: "can't be blank"
    		}
    	});
  	}

  	return res.status(200).json({
  		message: "You checked out at " + moment(new Date()).format(TIME_FORMAT) + ". Go home and enjoy your evening."
  	});
});

router.post('/status', function(req, res, next) {
  	if (!req.body.empid || !req.body.account || !req.body.localtime) {
    	return res.status(422).json({
    		errors: {
    			empid: "can't be blank",
    			account: "can't be blank"
    		}
    	});
  	}

  	return res.status(200).json({
  		status: 'checkin',
		time: '2012-04-23T13:25:43.511Z'
  	});
});

module.exports = router;