import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAddQuestionComponent } from './modal-add-question.component';

describe('ModalAddQuestionComponent', () => {
  let component: ModalAddQuestionComponent;
  let fixture: ComponentFixture<ModalAddQuestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalAddQuestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalAddQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
