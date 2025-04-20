import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DettaglioTorneoComponent } from './dettaglio-torneo.component';

describe('DettaglioTorneoComponent', () => {
  let component: DettaglioTorneoComponent;
  let fixture: ComponentFixture<DettaglioTorneoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DettaglioTorneoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DettaglioTorneoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
