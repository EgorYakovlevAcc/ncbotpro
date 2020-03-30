import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScoreRangeMessageComponent } from './score-range-message.component';

describe('ScoreRangeMessageComponent', () => {
  let component: ScoreRangeMessageComponent;
  let fixture: ComponentFixture<ScoreRangeMessageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScoreRangeMessageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScoreRangeMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
