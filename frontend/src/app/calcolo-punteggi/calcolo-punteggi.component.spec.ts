import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalcoloPunteggiComponent } from './calcolo-punteggi.component';

describe('CalcoloPunteggiComponent', () => {
  let component: CalcoloPunteggiComponent;
  let fixture: ComponentFixture<CalcoloPunteggiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CalcoloPunteggiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CalcoloPunteggiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
