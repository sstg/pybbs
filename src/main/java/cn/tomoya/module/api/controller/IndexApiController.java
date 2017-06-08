package cn.tomoya.module.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tomoya.config.base.BaseController;
import cn.tomoya.config.yml.SiteConfig;
import cn.tomoya.exception.ApiException;
import cn.tomoya.exception.Result;
import cn.tomoya.module.section.service.SectionService;
import cn.tomoya.module.topic.entity.Topic;
import cn.tomoya.module.topic.service.TopicService;

/**
 * Created by tomoya.
 * Copyright (c) 2016, All Rights Reserved.
 * http://tomoya.cn
 */
@RestController
@RequestMapping("/api")
public class IndexApiController extends BaseController {

  @Autowired
  private TopicService topicService;
  @Autowired
  private SiteConfig siteConfig;
  @Autowired
  private SectionService sectionService;

  /**
   * 话题列表接口
   * @param tab
   * @param p
   * @return
   * @throws ApiException
   */
  @GetMapping("/index")
  public Result index(String tab, Integer p) throws ApiException {
    if (!StringUtils.isEmpty(tab) && sectionService.findByName(tab) == null)
      throw new ApiException("版块不存在");
    if (StringUtils.isEmpty(tab))
      tab = "全部";
    Page<Topic> page = topicService.page(p == null ? 1 : p, siteConfig.getPageSize(), tab);
    return Result.success(page);
  }
}
