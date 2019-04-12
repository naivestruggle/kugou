# -*- coding: utf-8 -*-
"""
Created on Sat May 11 15:04:21 2019

@author: yyn
"""

import requests
import hashlib
import random
import sys


class KuGo_Spider(  object  ):
    def __init__(self):
        data=self.download_music(sys.argv[1])
        print(data)
        
    #下载歌曲信息  参数hash与album_id
    def download_music(self,Hash):
       
        tracker_url='https://wwwapi.kugou.com/yy/index.php?r=play/getdata&hash=%s'%Hash
        #生成md5加密后的cookie
        md5= hashlib.md5()
        n=random.sample('abcdefghijklmnopqrstuvwxyz0123456789',4)
        n=''.join(n)
        md5.update(n.encode())
        kg_mid=md5.hexdigest()
        headers={
            'Cookie':'kg_mid=%s'%kg_mid,
        }
        response=requests.get(tracker_url,headers=headers,timeout=5)
        result=response.json()['data']
        if len(result)==0:
            return None
        return result['play_url']   
        #相关信息
#        author_id=result['author_id']#歌手ID
#        author_name=result['author_name']#歌手名
#        audio_id=result['audio_id']#歌曲ID
#        audio_name=result['audio_name']#文件名
#        song_name=result['song_name']#歌曲名
#        hash_code=result['hash']#哈希编码
#        filesize=result['filesize']#文件大小  单位：字节   b   /1024/1024  MB
#        timelength=result['timelength']#时长  单位：毫秒   ms  /1000 秒
##        timelength=timelength/1000
##        timelength=str(int(timelength/60))+'分'+str(int(timelength%60))+'秒'#几分几秒
#        have_album=result['have_album']#是否有专辑
#        album_id=result['album_id']#专辑ID
#        album_name=result['album_name']#专辑名称
#        have_mv=result['have_mv']#是否有mv
#        video_id=result['video_id']#mvID
#        privilege=result['privilege']#权限
#        privilege2=result['privilege2']#权限二
#        play_url=result['play_url']#音频地址
#        img=result['img']#歌曲图片
#        lyrics=result['lyrics']#歌词
#        data=[author_id,author_name,audio_id,audio_name,song_name,hash_code,filesize,timelength,have_album,album_id,album_name,have_mv,video_id,privilege,privilege2,play_url,img,lyrics]
#        return data
    
if __name__=='__main__':
    KuGo_Spider()
