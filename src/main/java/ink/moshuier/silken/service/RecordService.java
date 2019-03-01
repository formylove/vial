package ink.moshuier.silken.service;

import ink.moshuier.silken.entity.Record;
import ink.moshuier.silken.service.base.BaseService;

public interface RecordService extends BaseService<Record>{

	Record getRecordByToken(String token);
}
