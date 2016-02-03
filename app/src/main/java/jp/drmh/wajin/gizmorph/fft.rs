#pragma version(1)
#pragma rs java_package_name(jp.drmh.wajin.gizmorph)
#include "common.rsh"

rs_allocation real;
rs_allocation img;
bool is_forward;
uint32_t len;
uint32_t levels;

uint16_t __attribute__((kernel)) root(uint16_t in, uint32_t x, uint32_t y){
    
    float realval[512];
    float imagval[512];
    
    if(is_forward){
        for (uint32_t i = 0; i < len; i++) {
            realval[i]=rsGetElementAt_float(real,x*512+i);
            imagval[i]=rsGetElementAt_float(img,x*512+i);
        }
    }else{
        for (uint32_t i = 0; i < len; i++) {
            realval[i]=rsGetElementAt_float(img,x*512+i);
            imagval[i]=rsGetElementAt_float(real,x*512+i);
        }
    }
    
    float costable[256],sintable[256];
    
    for (uint32_t i = 0; i < len / 2; i++) {
        costable[i]=cos(2 * M_PI * i / len);
        sintable[i]=sin(2 * M_PI * i / len);
    }
    
    // Bit-reversed addressing permutation
    for (uint32_t i = 0; i < len; i++) {
        uint32_t j = bit_reverse32(i);
        
        uint32_t ans=j>>(32 - levels);
        if (ans > i) {
            float temp = realval[i];
            realval[i] = realval[ans];
            realval[ans] = temp;
            temp = imagval[i];
            imagval[i] = imagval[ans];
            imagval[ans] = temp;
        }
    }
   
    
    for (uint32_t size = 2; size <= len; size *= 2) {
        uint32_t halfsize = size / 2;
        uint32_t tablestep = len / size;
        for (uint32_t i = 0; i < len; i += size) {
            for (uint32_t j = i, k = 0; j < i + halfsize; j++, k += tablestep) {
                float tpre=realval[j + halfsize] * costable[k]
                + imagval[j + halfsize] * sintable[k];
                float tpim = -realval[j + halfsize] * sintable[k]
                + imagval[j + halfsize] * costable[k];
                realval[j + halfsize] = realval[j] - tpre;
                imagval[j + halfsize] = imagval[j] - tpim;
                realval[j] += tpre;
                imagval[j] += tpim;
            }
        }
        if (size == len)
        break;
    }
        
    if(is_forward){
        for (uint32_t i = 0; i < len; i++) {
            rsSetElementAt_float(real, realval[i], x*512+i);
            rsSetElementAt_float(img, imagval[i], x*512+i);
        }
    }else{
        for (uint32_t i = 0; i < len; i++) {
            rsSetElementAt_float(real, imagval[i]/len, x*512+i);
            rsSetElementAt_float(img, realval[i]/len, x*512+i);
        }
        
    }
 
 return in;
}