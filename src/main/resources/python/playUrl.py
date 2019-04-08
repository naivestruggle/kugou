# -*- coding: utf-8 -*-
"""
Created on Thu May  2 15:49:39 2019

@author: PC-shujie
"""
import requests
import json
import sys
#'9E167AD621EF89EB2FAA0680D8F1598E'

def getPlay_url(h):
    url = 'https://wwwapi.kugou.com/yy/index.php?r=play/getdata&hash=%s'%h
    headers = {
            'accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
            'accept-encoding':'gzip, deflate, br',
            'accept-language':'zh-CN,zh;q=0.9',
            'cache-control':'max-age=0',
            'upgrade-insecure-requests':'1',
            'user-agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36'
            }
    response = requests.get( url, headers=headers )
    result = json.loads( response.text )
    return result['data']['play_url']

h = sys.argv[1]
print( getPlay_url(h) )