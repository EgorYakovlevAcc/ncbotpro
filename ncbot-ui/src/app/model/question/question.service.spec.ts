import { TestBed } from '@angular/core/testing';

import { QuestionServiceService } from '../../service/question-service.service';

describe('QuestionService', () => {
  let service: QuestionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
