package com.jiawa.wiki.controller;

import com.jiawa.wiki.req.EbookQueryReq;
import com.jiawa.wiki.req.EbookSaveReq;
import com.jiawa.wiki.resp.CommonResp;
import com.jiawa.wiki.resp.EbookQueryResp;
import com.jiawa.wiki.resp.PageResp;
import com.jiawa.wiki.resp.StatisticResp;
import com.jiawa.wiki.service.EbookService;
import com.jiawa.wiki.service.EbookSnapshotService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {
    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @GetMapping("/get-statistic")
    public CommonResp getStatistic() {
      List<StatisticResp> statisticResp = ebookSnapshotService.getStatistic();
      CommonResp<List<StatisticResp>> commonResp = new CommonResp<>();
      commonResp.setContent(statisticResp);
      return commonResp;
    }
}
