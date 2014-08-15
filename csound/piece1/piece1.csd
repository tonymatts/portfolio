<CsoundSynthesizer>

sr = 44100
kr = 4410
ksmps = 10
nchnls = 1

<CsInstruments>
gar init 0
gadel init 0
gideltime init 0
gidelfback init 0

instr 1

kamp expseg 5000, p3/2, 10000, p3/2, 10000
ipitch = p4
iden = p5
iampoff = p6
ipitoff = p7
kgdur line p10, p3, p11
isft = p8
ienv = p9
islngth = ftlen(isft)
isftt = islngth/sr

	a1 grain kamp, ipitch, iden, iampoff, ipitoff, kgdur, isft, ienv, isftt
	out a1

endin

instr 2 
idur = p3
kamp = p4
ia = cpspch(p5)
ib = cpspch(p6)
ifn = p7

	k1 line ia, idur, ib
	a1 oscili kamp, k1, ifn
	out a1
	gar = gar + a1
endin

instr 20


endin

instr 21
krvt = 2

  a3 reverb gar, krvt
  out a3 
  gar = 0 
endin

instr 3
idur = p3
kamp = p4
ia = p5
ib = p6
kcps = p7
ifn = p8

	k1 line ia, idur, ib
	a2 mpulse 50, k1
	a1 oscili kamp, kcps*a2, ifn 
	out a1

endin

instr 4
idur = p3
iamp = p4
kamp = p5
ifreq = p6
ia = p7
ib =p8
ifn = p9

	k1 line ia, idur, ib
	k2 rand ifreq
	a3 rand iamp*k1
	a1 oscili kamp, k2, ifn
	a2 balance a1, a3
	out a2

endin

</CsInstruments>

<CsScore>
f1 0 4096 10 1
f2 0 128 10 1
f3 0 4096 7 0 512 1 2 -1 256 1 ;Sawtooth
f5 0 32768 1 "claps.aif" 0 4 0 ; 

i1 0 1 15 4 100 1000 5 1 .05 .05
i1 2 1 15 6 100 1000 5 1 .05 .01
i1 3 1 15 4 100 1000 5 1 .05 .05
i1 4 1 15 2 100 1000 5 1 .05 .0005
i1 6 1 15 4 100 1000 5 1 .05 .05
i1 7 13 15 6 100 1000 5 1 .05 .0005
i1 8 14 15 4 100 1000 5 1 .05 .05
i1 0 30 150 2 1 10000 5 5 .00009 .9

i2 0 .1 5000 6.02 15.00 2
i2 + . 5000 6.02 -15.00 2
i2 . . 5000 6.02 15.00 2
i2 . . 5000 6.02 -5.020 2
i2 . . 5000 6.02 15.00 2
i2 . . 5000 6.02 -15.02 2
i2 . . 5000 6.02 15.00 2
i2 . . 5000 6.02 -5.00 2
i2 . . 5000 6.02 15.00 2
i2 . . 5000 6.02 -5.00 2
i2 . . 5000 6.02 15.00 2
i2 . . 5000 6.02 -5.00 2


i2 9.8 .1 2000 6.02 -140.00 3
i2 10 .2 2000 6.02 -140.00 3
i2 10.3 .3 2000 6.02 -140.00 3
i2 10.7 .4 2000 6.02 -140.00 3
i2 12.2 .5 2000 6.02 -140.00 2
i2 12.7 .7 2000 6.02 -140.00 2
i2 13.4 .5 2000 6.02 -140.00 2
i2 13.9 .5 2000 6.02 -140.00 2
i2 14.4 .7 2000 6.02 -140.00 2
i2 15.1 .5 2000 6.02 -140.00 2



i3 10 3 2000 .0001 .00001 840 1
i3 13 2 3000 .0001 .001 840 1

i4 0 3 10 10000 100000000 .1 100 3
i4 1 4 10 10000 80000000 .1 100 3
i4 2 5 10 10000 60000000 .1 100 3
i4 3 6 10 10000 40000000 .1 100 3
i4 4 7 10 10000 20000000 .1 100 3
i4 5 8 10 10000 10000000 .1 100 3
i4 6 9 10 10000 8000000 .1 100 3
i4 7 10 10 10000 6000000 .1 100 3
i4 8 11 10 10000 4000000 .1 100 3

i4 0 10 50 10000 4000 .1 100 3

i4 .1 .2 100 10000 4500 .1 100 3
i4 .2 .3 100 10000 1000 .1 100 3
i4 .5 .2 100 10000 4000 .1 100 3
i4 .6 .3 100 10000 4000000 .1 100 3
i4 1 .2 100 10000 4000000 .1 100 3
i4 1.1 .3 100 10000 450 .1 100 3
i4 1.2 .2 100 10000 4000000 .1 100 3
i4 1.3 .3 100 10000 4000000 .1 100 3
i4 2 .2 100 10000 400 .1 100 3
i4 2.1 .3 100 10000 400 .1 100 3
i4 2.2 .2 100 10000 400 .1 100 3
i4 2.3 .3 100 10000 4000000 .1 100 3
i4 2.4 .2 100 10000 4000 .1 100 3
i4 5 .2 100 10000 4000000 .1 100 3
i4 5.1 .3 100 10000 400 .1 100 3
i4 5.5 .2 100 10000 400 .1 100 3
i4 5.6 .3 100 10000 4000000 .1 100 3
i4 6 .2 100 10000 450 .1 100 3
i4 6.2 .2 100 10000 450 .1 100 3
i4 7 .2 100 10000 450 .1 100 3
i4 7.2 .2 100 10000 450 .1 100 3
i4 8 .2 100 10000 450 .1 100 3
i4 8.2 .2 100 10000 450 .1 100 3
i4 10 .2 100 10000 450 .1 100 3
i4 10.2 .2 100 10000 450 .1 100 3
i4 12 .2 100 10000 450 .1 100 3

</CsScore>
</CsoundSynthesizer>

<bsbPanel>
 <label>Widgets</label>
 <objectName/>
 <x>624</x>
 <y>69</y>
 <width>400</width>
 <height>404</height>
 <visible>true</visible>
 <uuid/>
 <bgcolor mode="nobackground">
  <r>231</r>
  <g>46</g>
  <b>255</b>
 </bgcolor>
 <bsbObject version="2" type="BSBVSlider">
  <objectName>slider1</objectName>
  <x>5</x>
  <y>5</y>
  <width>20</width>
  <height>100</height>
  <uuid>{ff2a104a-fc81-4312-8be0-a981bfaf28b0}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>-3</midicc>
  <minimum>0.00000000</minimum>
  <maximum>1.00000000</maximum>
  <value>0.00000000</value>
  <mode>lin</mode>
  <mouseControl act="jump">continuous</mouseControl>
  <resolution>-1.00000000</resolution>
  <randomizable group="0">false</randomizable>
 </bsbObject>
</bsbPanel>
<bsbPresets>
</bsbPresets>
<EventPanel name="" tempo="60.00000000" loop="8.00000000" x="320" y="218" width="596" height="322" visible="true" loopStart="0" loopEnd="0">    </EventPanel>
