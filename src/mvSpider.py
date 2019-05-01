import requests
import json
import sys
# http://soapi.yinyuetai.com/search/video-search?keyword=知否知否&pageIndex=1&pageSize=24&offset=0&orderType=TOTALVIEWS
# http://ext.yinyuetai.com/main/get-h-mv-info?json=true&videoId=2275893

class getMv:
    def getVideoId(self, keyword):
        url = 'http://soapi.yinyuetai.com/search/video-search?keyword=%s&pageIndex=1&pageSize=24&offset=0&orderType=TOTALVIEWS'%keyword
        headers = {
            'Host':'soapi.yinyuetai.com',
            'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36'
        }
        response = requests.get( url, headers=headers )
        result = json.loads(response.text)
        videoData = result['videos']['data'][0]   # 只取最热门的那个MV
        videoId = videoData['id']
        return videoId

    def getVideo(self, mvId):
        url = 'http://ext.yinyuetai.com/main/get-h-mv-info?json=true&videoId=%s'%mvId
        headers = {
            'Host':'ext.yinyuetai.com',
            'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36'
        }
        response = requests.get( url, headers=headers )
        result = json.loads(response.text)
        coreVideoInfo = result['videoInfo']['coreVideoInfo']
        #print( coreVideoInfo )
        artistNames = coreVideoInfo['artistNames']
        videoName = coreVideoInfo['videoName']
        videoUrlModels = coreVideoInfo['videoUrlModels']
        video = {}  # 存全部信息
        videoModels = []   # 存MV全部清晰度和地址
        for v in videoUrlModels:
            videoType = {}  # 存各个清晰度与地址
            videoType[v['qualityLevelName']]=v['videoUrl']
            videoModels.append(videoType)
        videoInfo = {}
        videoInfo['video'] = videoModels
        videoInfo['headImage'] = coreVideoInfo['bigHeadImage']


        video[artistNames+' - '+videoName] = videoInfo
        return video

    def main(self, keyword):
        videoId = self.getVideoId( keyword )
        videoInfo = self.getVideo( videoId )
        return videoInfo

if __name__=='__main__':
    mv = getMv()
    # print(mv.getVideo('2275893'))
    # print( mv.getVideoId( '那英 默' ) )

#    mvName = '毛不易 消愁'
    mvName = sys.argv[1]
    print( mv.main( mvName ) )