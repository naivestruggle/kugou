#下载歌曲信息  参数hash与album_id
import sys
import requests
import hashlib
import random

def download_music(Hash):
    tracker_url='https://wwwapi.kugou.com/yy/index.php?r=play/getdata&hash=%s'%Hash
    #生成md5加密后的cookie
    #创建md5对象
    md5= hashlib.md5()
    #随机生成4位随机的字符列表 范围为a-z 0-9
    n=random.sample('abcdefghijklmnopqrstuvwxyz0123456789',4)
    #将列表元素拼接为字符串
    n=''.join(n)
    #将字符串编码后更新到md5对象里面
    md5.update(n.encode())
    #调用hexdigest获取加密后的返回值
    kg_mid=md5.hexdigest()
    headers={
        'Cookie':'kg_mid=%s;KuGoo=KugooID=1457232077&KugooPwd=A9E7210FE916EA5CCD18D3456C5FEA79&t=28569e0079123149a68c1d41805898ae8d8aa2121439dd88041d085ec346ae60'%kg_mid,
    }
    response=requests.get(tracker_url,headers=headers,timeout=5)
    result=response.json()['data']
    play_url=result['play_url']#音频地址
    return play_url
h = sys.argv[1]
#download_music('A774A794184AF17469B27963E120B565')
download_music(h)